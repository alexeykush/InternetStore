<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <#include "css/main.css">

    <title>Document</title>
</head>
<body>

    <main>

        <#if !authorized>
        <a class="main-page-link red" href="/reg">Sign up</a>

        <a  class="main-page-link purple" href="/login">Sign in</a>

        </#if>

        <a class="main-page-link green" href="/list">List</a>

        <#if authorized>
            <a class="main-page-link purple" href="/cart">Cart</a>

            <a class="main-page-link red" href="/?logout=true">Log out</a>
        </#if>

    </main>
</body>
</html>