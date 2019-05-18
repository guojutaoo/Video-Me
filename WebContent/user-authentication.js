var v = null;

function jumpToRegister(){                     //login page jumps to register page
    document.forms.login_form.action="register.html";
}

function jumpToLogin(){                         //register page jumps back to login page
    document.forms.register_form.action="login.html";
}

function reCapatchaVerify(){             //googleReCaptcha
    var v = grecaptcha.getResponse();
    if (v.length==0){
        alert("false");
        return false;
    }else{
        alert("true");
        return true;
    }
}

function signin(){
    alert("v = " + v);
}
