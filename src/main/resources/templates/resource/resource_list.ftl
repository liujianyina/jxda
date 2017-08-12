<!DOCTYPE html>

<html>
<head>
<#include "../_header.ftl">
    <title>${username}文件列表</title>
</head>
<body>
<#include "../_top.ftl">
<#include "../_menu.ftl">
<div class="content">
<#include "../_common.ftl">
    <div class="resource-list">
        <table>
            <tr>
                <th class="selectTd">
                </th>
                <th class="file-name">
                    文件名
                </th>
                <th class="file-type">文件类别
                </th>
                <th class="file-size">
                    大小
                </th>
                <th class="file-user">
                    上传人
                </th>
                <th class="file-time">
                    上传时间
                </th>
                <th class="file-operation">
                    操作
                </th>
            </tr>
        <#list resources as resource>
            <tr class="resource-list-item">
                <td class="selectTd">
                    <img src="/static/img/select.png" class="singleSelect" id="${resource.tempName}" select="false"/>
                </td>
                <td class="file-name">
                ${resource.cname}
                </td>
                <td class="file-type">
                ${resource.type}
                </td>
                <td class="file-size">
                ${resource.size}kb
                </td>
                <td class="file-user">
                ${resource.username}
                </td>
                <td class="file-time">
                ${resource.createTime?string("yyyy年MM月dd日 HH:mm:ss")}
                </td>
                <td class="file-operation">
                    <a target="_blank" href="/resource/preview?taken=${resource.tempName}">预览</a>
                    <a href="/resource/delete?taken=${resource.tempName}">删除</a>
                </td>
            </tr>
        </#list>
        </table>
    </div>
    <div class="page">
        <div class="page-box">
            <a href="/resource/resource_list?pageIndex=${pageIndex - 1}&pageSize=${pageSize}">上一页</a>
            <a href="/resource/resource_list?pageIndex=${pageIndex + 1}&pageSize=${pageSize}">下一页</a>
        </div>
    </div>
    <div id="download-box" style="display: none">

    </div>
</div>
<#include "../_upload.ftl">
<#include "../_footer.ftl">
<#include "../_flash.ftl">
</body>
</html>