jquery(function ($) {
    $(document).ready(function () {
        const data = getDataSearch();
        getListUser(data);

        function getListUser(data) {
            $.ajax({
                url: '/api/user',
                type: 'GET',
                data: JSON.stringify(data),
                dataType:'json',
                contentType: "application/json",
                beforeSend: function () {
                    $('.loader').css("display", "block");
                    $('#pagination-test').empty();
                    $('#pagination-test').removeData("twbs-pagination");
                    $('#pagination-test').unbind("page");
                },
                success: function (res) {
                    $('.loader').css("display", "none");
                    var dt = res.data;
                    if (dt.totalPage != 0) {
                        paging(dt.totalPage,dt.pageIndex+1);
                    }
                    showUser(dt.data)
                },
                error: function (e) {
                    alert("false");
                    console.log(e);
                }
            })
        }

        $('#btn_search').click(function () {
            var data = getDataSearch();
            getListUser(data);
        });


        function paging(totalPage,currentPage) {
            $('#pagination-demo').twbsPagination({
                totalPages: totalPage,
                startPage: currentPage,
                visiblePages: 10,
                last:'Cuối cùng',
                next:'Tiếp theo',
                first:'Đầu tiên',
                prev:'Prev',
                onPageClick: function (event, page) {
                    console.log("start" + page);
                    if (page) {
                        console.log("second"+page);
                        console.log("current"+currentPage);
                        //ddieeuf kieenj nay sai
                        if (currentPage !== page) {
                            console.log(page);
                            var data = getDataSearch();
                            data["pageIndex"] = page;
                            currentPage = page;
                            console.log(data);
                            getListUser(data);
                        }
                    }

                }
            });
        }

        function showUser(data) {
            var s = '';
            if (data.length === 0) {
                var s1 = `<div class="alert alert-warning text-center w-100 mt-3" style="color: #f6821f;  background-color: #fff3cd; border-color: #ffeeba; margin-top: 200px;">
             <i class="fa fa-exclamation-triangle"></i>
             <a style="color: #f6821f;">Không tìm thấy blog nào !</a>`;
                s += `   <tr style="background-color: white">
                <td colspan="100">` + s1 + `</td>
                </tr> `;
                $("#data-list").html(s1);
            }
            $(data).each(function (index, v) {
                s += '<tr>'
                    + '<td><input type="checkbox" name="idCoursePlan" id="checkbox_' + v.id + '" value="' + v.id + '"></td>'
                    // + '<td>' + v.status + '</td>'
                    // + '<td>' + v.name + '</td>'
                    + '<td>' + v.username + '</td>'
                    + '<td>' + v.email + '</td>'
                    + '<td>' + v.phone + '</td>'
                    + '<td>' + v.address + '</td>'
                    + '<td>' + v.sex + '</td>'
                    + '<td>' + fomatDate(v.createdDate) + '</td>'
                    + '<td><button><a href="/admin/user-edit/' + v.id + '">Chi tiết</a></button></td>'
                    + '</tr>';

            });
            $('#data-list').html(s);

        }

        function fomatDate(date) {
            var day = '';
            var month = "";
            var year = '';
            $(date).each(function (index, v) {
                if (index == 0) {
                    year = v;
                } else if (index == 1) {
                    month = v;
                } else {
                    day = v;
                }
            });
            return day + '/' + month + '/' + year;
        }

        function getDataSearch() {
            var data = {};
            var formSearch = $('#search').serializeArray();

            $.each(formSearch, function (i, v) {
                data["" + v.name + ""] = v.value;
            });

            return data;
        }
    })
})