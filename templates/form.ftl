<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <#include "css/form.css">
</head>
<body>


<main>
    <form action=${rout} method="post">

    <#list fields as field>

        <#if field == "password">
            <input class="form-field" type="password" name="password" placeholder="password">
        <#else>
            <input class="form-field" type="text" name=${field} placeholder=${field}>
        </#if>
    </#list>
        <input class="submit" type="submit">
    </form>
    <a class="back" href="/">Back to menu</a>
</main>

</body>

</html>