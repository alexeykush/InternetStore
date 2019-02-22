<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <#include "css/cart.css">
    <title>Document</title>
</head>
<body>

    <main>
        <div class="commodity-row">
            <span class="commodity">Index</span>
            <span class="commodity">Name</span>
            <span class="commodity">Price</span>
            <span class="commodity">Quantity</span>
        </div>
       <#list goods as commodity>
           <div class="commodity-row">
               <span class="commodity">${commodity?counter}</span>
               <span class="commodity">${commodity.name}</span>
               <span class="commodity">${commodity.price}$</span>
               <span class="commodity">${commodity.quantity}</span>
               <div class="modifying">
                   <a class="modifying-item" href="handle/add?commodityId=${commodity.commodityId}">+</a>
                   <a class="modifying-item" href="handle/remove?commodityId=${commodity.commodityId}">-</a>
                   <a class="modifying-item" href="handle/delete?commodityId=${commodity.commodityId}">x</a>
               </div>
           </div>

       </#list>
        <a class="back" href="/">Back to menu</a>
    </main>
    <p class="total">Total: #{total}$</p>



</body>
</html>