<html>
<head>
    <title>入库信息</title>
    <link href="${request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body style="margin-top:50px;overflow: hidden;">
<form action="${request.contextPath}/inventory/putGoods" method="post">
    <input type="hidden" name="goodsId" value="<#if goods.id??>${goods.id?c}</#if>"/>
    <table class="gridtable" style="width:800px;">
        <tr>
            <th colspan="5">入库信息 - [<a href="${request.contextPath}/goods">返回</a>]</th>
        </tr>
        <tr>
            <th>商品名称：</th>
            <td>${goods.name}
            </td>
            <th>入库数量：</th>
            <td><input type="text" name="number" value="0"/></td>
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
