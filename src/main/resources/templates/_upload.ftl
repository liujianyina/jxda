<!--
    作者：18523245126@sina.com
    时间：2017-08-07
    描述：点击上传文档时弹出来的窗口
-->
<div class="pop-window" id="upload-window">
    <div class="pop-window-title">
        上传文件
    </div>
    <div class="closeButton" id="upload-window-close">
        +
    </div>
    <form action="/resource/upload" method="post" enctype="multipart/form-data">
        <div class="upload-window-box">
            <p class="notice">
                选择文件
            </p>
            <input type="text" readonly="readonly" class="filename-display" id="filename-display" /><label class="choise-file-button" id="choise-file-button">选择文件</label>
            <input type="file" name="file" id="file-input" />
        </div>
        <div class="upload-window-box">
            <p class="notice">
                选择文件夹
            </p>
            <select class="save-address" name="dirname">
                <option>我的文档</option>
            </select>
        </div>
        <div class="upload-window-box" style="height: 150px;">
            <p class="notice">
                哪些人可见？
            </p>
            <div id="role-box">
            </div>
        </div>
        <div class="upload-window-box">
            <input type="submit" value="提交" class="upload-file-submit" />
        </div>
    </form>
</div>