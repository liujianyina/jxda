<!--
    作者：18523245126@sina.com
    时间：2017-08-07
    描述：顶部
-->
<div class="nav underline">

    <div class="logo x">
        <a href="/index">
            <img src="/static/img/logo.png" />
        </a>
    </div>

    <div class="project x">
        教学档案管理系统
    </div>

    <div class="about y">
        <a href="/about_us">关于我们</a>
        <span>丨</span>
        <a href="/link_us">联系我们</a>
    </div>

    <#if username??>
        <div class="user-box y">
            欢迎您，${username}
        </div>
    </#if>
</div>
