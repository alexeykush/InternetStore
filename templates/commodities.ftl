<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <#include "css/commodities.css">

    <title>Document</title>
</head>
<body>
    <main>
        <#list commodities as commodity>
            <div class="commodity-row">
                <span class="commodity">${commodity.id}</span>
                <span class="commodity">${commodity.name}</span>
                <span class="commodity">${commodity.price}$</span>
                <#if authorized>
                    <a class="add" href="handle/add?commodityId=${commodity.id}">Add</a>
                </#if>
            </div>
        </#list>
        <a class="back" href="/">Back to menu</a>
    </main>

</body>
</html>