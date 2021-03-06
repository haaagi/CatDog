var main = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        })
        $('#btn-update').on('click', function () {
            _this.update();
        })
        $('#btn-delete').on('click', function () {
            _this.delete();
        })
        $('#btn-user-save').on('click', function () {
            _this.user_save();
        })
        $('#btn-test-save').on('click', function () {
            _this.test_save();
        })
        $('#btn-like').on('click', function () {
            _this.likesup();
        })
        $('#btn-signin').on('click', function () {
            _this.signin();
        })
    },
    signin: function() {
        var data = {
            email: $('#email').val(),
            password: $('#password').val()
        };
        $.ajax({
            type: 'POST',
            url: '/jwtapi/user/signin',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('로그인 성공');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        var id = $('#id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    user_save: function () {
        var data = {
            email : $('#email').val(),
            password : $('#password').val(),
            name: $('#name').val(),
            nickname: $('#nickname').val(),
            birthday: $('#birthday').val(),
            picture: $('#picture').val(),
            role: $('#role').val()
        };
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '/api/v1/user',
            contentType: 'application/json',
            data: JSON.stringify(data)
        }).done(function () {
            alert('회원이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    test_save : function () {
        var data = {
            title: $('#title').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/test',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    likesup: function () {
        var data = {
            pid: $('#id').val(),
            email: $('#author').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/likesup',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('좋아요!');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
main.init();
main.list();