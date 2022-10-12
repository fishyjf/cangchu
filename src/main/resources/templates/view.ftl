<html>
<head>
    <title>国家(地区)信息</title>
    <link href="${request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body style="margin-top:50px;overflow: hidden;">
<form action="${request.contextPath}/goods/save" method="post">
    <input type="hidden" name="id" value="<#if goods.id??>${goods.id?c}</#if>"/>
    <table class="gridtable" style="width:800px;">
        <tr>
            <th colspan="5">商品信息 - [<a href="${request.contextPath}/goods">返回</a>]</th>
        </tr>
        <tr>
            <th>商品名称：</th>
            <td><input type="text" name="name" value="<#if goods.name??>${goods.name}</#if>"/>
            </td>
            <td><input type="submit" value="保存"/></td>
        </tr>
    <#if msg??>
        <tr style="color:#00ba00;">
            <th colspan="5">${msg}</th>
        </tr>
    </#if>
    </table>
</form>
</body>
</html>
