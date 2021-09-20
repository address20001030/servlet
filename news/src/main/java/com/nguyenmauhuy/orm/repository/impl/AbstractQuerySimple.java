package com.nguyenmauhuy.orm.repository.impl;

//import com.google.protobuf.ListValue;

import com.nguyenmauhuy.orm.annotation.Id;
import com.nguyenmauhuy.orm.exception.ORMException;
import com.nguyenmauhuy.orm.paging.Page;
import com.nguyenmauhuy.orm.paging.PageAble;
import com.nguyenmauhuy.orm.paging.PageImpl;
import com.nguyenmauhuy.orm.pool.ConnectionPool;
import com.nguyenmauhuy.orm.repository.JpaRepository;
import com.nguyenmauhuy.orm.repository.builder.Query;
import com.nguyenmauhuy.util.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.nguyenmauhuy.orm.util.AnnotationUtils.*;
import static com.nguyenmauhuy.util.ReflectionUtil.*;

public class AbstractQuerySimple<T, ID> implements JpaRepository<T, ID> {
    private Class<T> tClass;
    private final String tableName;
    protected final ConnectionPool connectionPool;
    private String save;
    private String update;
    private String select;
    private String delete;
    private String count;

    public AbstractQuerySimple() {
        this.connectionPool = new ConnectionPool();
        this.tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.tableName = getClassName(tClass);
        this.save = "INSERT INTO " + tableName + "(%s)" + "VALUES(%s)";
        this.update = "UPDATE " + tableName + " SET %s WHERE %s";
        this.select = "SELECT * FROM " + tableName;
        this.delete = "DELETE FROM " + tableName + " WHERE %s";
        this.count = "SELECT COUNT(1) as total FROM " + tableName;
    }

    @Override
    public void save(T t) {
        Field[] fields = tClass.getDeclaredFields();
        StringBuilder colums = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<Object> listValue = new ArrayList<>();

        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            if (fields[i].isAnnotationPresent(Id.class)) {
                try {
                    boolean increment = getAutoIncrement(tClass, name);
                    if (!increment) {
                        colums.append(getPrimaryKey(tClass, name)).append(",");
                        values.append("?,");
                        listValue.add(get(t, fields[i]));
                    }
                } catch (Exception e) {
                    throw new ORMException(e.getMessage());
                }
                continue;

            }
            try {
                colums.append(getColumnName(tClass, name)).append(",");
                values.append("?,");
                listValue.add(get(t, fields[i]));
            } catch (Exception e) {
                e.printStackTrace();
                throw new ORMException(e.getMessage());
            }
        }
        colums.deleteCharAt(colums.length() - 1);
        values.deleteCharAt(values.length() - 1);

        save = String.format(save, colums.toString(), values.toString());
        Connection crete = null;
        try {
            crete = connectionPool.getConnection();
            crete.setAutoCommit(false);
            PreparedStatement ps = crete.prepareStatement(save);
            int i = 1;
            for (Object object : listValue) {
                ps.setObject(i++, object);
            }

            ps.executeUpdate();
            crete.commit();
        } catch (SQLException throwables) {
            try {
                crete.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ORMException(e.getMessage());
            }
        } finally {
            if (crete != null) {
                try {
                    crete.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    throw new ORMException(throwables.getMessage());
                }
            }
        }
    }

    @Override
    public void update(ID id, T t) {
        Field[] fields = tClass.getDeclaredFields();
        StringBuilder setStatement = new StringBuilder();
        List<Object> values = new ArrayList<>();
        for (int i = 1; i < fields.length; i++) {
            String name = fields[i].getName();
            try {
                if (ReflectionUtil.get(t, fields[i]) != null){
                    setStatement.append(getColumnName(tClass, name)).append("=?,");
                    values.add(get(t, fields[i]));
                }
            } catch (Exception e) {
                throw new ORMException(e.getMessage());
            }

        }
        Connection connection = null;
        try {

            String where = getPrimaryKey(tClass, fields[0].getName()) + "=?";
            values.add(id);
             setStatement.deleteCharAt(setStatement.length()-1);
            this.update = String.format(this.update, setStatement.toString(), where);
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(this.update);
            int j = 1;
            for (Object object : values) {
                ps.setObject(j++, object);
            }
            ps.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            try {
                assert connection!=null;
                connection.rollback();
            } catch (SQLException throwables) {
                throw new ORMException(e.getMessage());
            }

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    @Override
    public void delete(ID id) {
        this.delete = String.format(this.delete, " id = ?");
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(this.delete);
            ps.setObject(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (Exception exception) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    @Override
    public void deleteAll(List<ID> ids) {
        String inCondition = ids.stream().map(String::valueOf).collect(Collectors.joining(","));
        this.delete = String.format(delete, "id in (" + inCondition + ")");
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(this.delete);
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throw new ORMException(e.getMessage());
            }

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    @Override
    public Optional<T> findById(ID id) {
        StringBuilder findById = new StringBuilder(this.select);
        findById.append(" WHERE id = ?");
        try (Connection connection = connectionPool.getConnection()) {
//            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(findById.toString());
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            T t = null;
            if (rs.next()) {
                t = convertToEntity(rs, tClass);
            }
            return Optional.of(t);


        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchFieldException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Stream<T> findAll() {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(this.select);
            ResultSet rs = ps.executeQuery();
            List<T> list = new LinkedList<>();
            T t;
            while (rs.next()) {
                t = convertToEntity(rs, tClass);
                list.add(t);
            }
            return list.stream();
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchFieldException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public long count() {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(this.count);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("total");
            }
            return 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Page<T> findAll(PageAble pageAble) {
        StringBuilder selectPage = new StringBuilder(this.select);
        if (pageAble.getIndex() != 0 && pageAble.getSize() != 0) {
            selectPage.append(" LIMIT ?").append(" OFFSET ?");
        }
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(selectPage.toString());
            ps.setInt(1, pageAble.getSize());
            ps.setInt(2, pageAble.getOffset());
            ResultSet resultSet = ps.executeQuery();
            List<T> list = new LinkedList<>();
            T t;
            while (resultSet.next()) {
                t = convertToEntity(resultSet, tClass);
                list.add(t);
            }

            long totalItem = count();
            Page<T> page = new PageImpl<T>(pageAble.getIndex(), pageAble.getSize(), totalItem, list);
            return page;
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchFieldException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<T> find(Query<T> query) {
        StringBuilder sqlBuilder = new StringBuilder(this.select);
        if (StringUtils.isNotEmpty(query.condition())) {
            sqlBuilder.append(" WHERE ").append(query.condition());
        }
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sqlBuilder.toString());

            if (query.value() != null) {
                ps.setObject(1, query.value());
            } else if (query.values() != null) {
                int j = 1;
                for (Object value : query.values()) {
                    ps.setObject(j++, value);
                }
            }

            ResultSet rs = ps.executeQuery();
            T t = null;
            if (rs.next()) {
                t = convertToEntity(rs, tClass);
            }
            return Optional.of(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Page<T> findAll(Query<T> query, PageAble pageAble) {
        StringBuilder sqlBuilder = new StringBuilder(this.select);
        if (StringUtils.isNotEmpty(query.condition())) {
            sqlBuilder.append(" WHERE ").append(query.condition());
        }
        if (pageAble != null) {
            sqlBuilder.append(" LIMIT ? OFFSET ? ");
        }
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sqlBuilder.toString());
            int j = 1;
            if (query.value() != null) {
                ps.setObject(j++, query.value());
            } else if (query.values() != null) {
                for (Object value : query.values()) {
                    ps.setObject(j++, value);
                }
            }

            ps.setInt(j++, pageAble.getSize());
            ps.setInt(j, pageAble.getOffset());

            ResultSet rs = ps.executeQuery();
            List<T> list = new LinkedList<>();
            T t = null;
            while (rs.next()) {
                t = convertToEntity(rs, tClass);
                list.add(t);
            }

            var totalItem = count(query);

            return Page.of(pageAble.getIndex(), pageAble.getSize(), totalItem, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Page.empty();
    }

    @Override
    public long count(Query<T> query) {
        StringBuilder sqlBuilder = new StringBuilder(this.count);
        if (StringUtils.isNotEmpty(query.condition())) {
            sqlBuilder.append(" WHERE ").append(query.condition());
        }
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sqlBuilder.toString());

            if (query.value() != null) {
                ps.setObject(1, query.value());
            } else if (query.values() != null) {
                int j = 1;
                for (Object value : query.values()) {
                    ps.setObject(j++, value);
                }
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}