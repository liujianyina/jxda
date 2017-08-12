<#import "/spring.ftl" as spring>
<#if ERROR_MESSAGE??>
    <@spring.messageText ERROR_MESSAGE, ERROR_MESSAGE />
</#if>

<#if INFO_MESSAGE??>
    <script type="text/javascript">
        alert(<@spring.messageText INFO_MESSAGE, INFO_MESSAGE />);
    </script>
</#if>