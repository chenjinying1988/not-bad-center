


function loginBiji() {
    if (!$('#login-username').val()) {
        alert("账号/邮箱/手机 不能为空");
        return ;
    }
    if (!$('#login-password').val()) {
        alert("密码不能为空");
        return ;
    }

    $.ajax({
        url : 'login',
        type : 'post',
        dataType : 'json',
        data : {
            "account" : $('#login-username').val(),
            "password" : $('#login-password').val()
        },
        success : function(json) {
            if (json.code === 200) {
                location.href = '/admin';
            } else {
                alert("登录失败: " + json.message);
            }
        }
    });
}

function register() {
    if (!$('#register-account').val()) {
        alert("账号不能为空");
        return ;
    }

    if (!$('#register-nickname').val()) {
        alert("昵称不能为空");
        return ;
    }

    if (!$('#register-password').val()) {
        alert("密码不能为空");
        return ;
    }

    $.ajax({
        url : 'register',
        type : 'post',
        dataType : 'json',
        data : {
            "account"  : $('#register-account').val(),
            "nickname" : $('#register-nickname').val(),
            "email"    : $('#register-email').val(),
            "phone"    : $('#register-phone').val(),
            "password" : $('#register-password').val()
        },
        success : function(json) {
            if (json.code === 200) {
                location.href = '/admin';
            } else {
                alert("注册失败: " + json.message);
            }
        }
    });
}