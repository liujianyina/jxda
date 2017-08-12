<!DOCTYPE html>

<html>
    <head>
        <#include "_header.ftl">
        <title>欢迎使用本系统，请登录...</title>
    </head>
    <body>
        <#include "_top.ftl">

        <!--
            作者：18523245126@sina.com
            时间：2017-08-07
            描述：登录
        -->
        <div class="login-body underline">
            <div class="login-body-letf x">
                <img src="/static/img/timg.jpg"/>
            </div>
            <div class="login-box y">
                <div class="login-title">
                    欢迎使用
                </div>

                <div class="error-message" id="error-message">
                    <#include "_flash.ftl">
                </div>

                <div class="login-form">
                    <form action="/login" method="post">
                        <div class="login-component">
                            <input type="text" name="username" placeholder="用户名" />
                        </div>
                        <div class="login-component">
                            <input type="password" name="password" placeholder="密　码" />
                        </div>
                        <div class="login-component">
                            <input type="text" name="captcha" maxlength="6" placeholder="验证码" style="width: 40%;" /><img src="/captcha" id="captcha"/>
                        </div>
                        <div class="login-component">
                            <input type="submit" value="登录" class="input-submit"/>
                        </div>
                    </form>
                </div>
                <div class="forgert-password">
                    <a href="#">忘记密码？</a>
                </div>
            </div>
        </div>

        <#include "_copyright.ftl">

        <#include "_footer.ftl">
        <script type="text/javascript">
            /**
             * 2S后错误警告框消失
             */
            function clear() {
                $('#error-message').text('');
            }
            setInterval(clear, 2000);

            $('#captcha').click(function () {
                $(this).attr("src","/captcha?math=" + Math.random());
            });
        </script>
    </body>
</html>