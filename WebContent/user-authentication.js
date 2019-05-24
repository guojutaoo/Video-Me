
function jumpToRegister(){                     //login page jumps to register page
    document.forms.login_form.action="register.html";
}


function jumpToLogin(){                         //register page jumps back to login page
    document.forms.register_form.action="login.html";
}


function reCaptchaVerify(){             //googleReCaptcha
    var v = grecaptcha.getResponse();
    if (v.length==0){
        document.forms.login_form.action="Login?recaptcha=false";
    }else{
        document.forms.login_form.action="Login?recaptcha=true";
    }
}

