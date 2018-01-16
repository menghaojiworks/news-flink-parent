<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" name="viewport">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta name="applicable-device" content="pc,mobile">
    <meta name="MobileOptimized" content="width"/>
    <meta name="HandheldFriendly" content="true"/>
    <meta name="author" content="${author}">
    <meta name="keywords" content="${keywords}">
    <meta name="description" content="${description}">
    <title>${title}</title>
    <script type="text/javascript" src="https://mini.eastday.com/toutiaoh5/js/responsive.min.js"></script>
    <link rel="stylesheet" href="https://mini.eastday.com/toutiaoh5/css/photoswipe/photoswipe.min.css">
    <link rel="stylesheet" href="https://mini.eastday.com/toutiaoh5/css/common.min.css">
    <link rel="stylesheet" href="https://mini.eastday.com/toutiaoh5/css/page_details_v5.min.css">

</head>
<body>
<input type="hidden" value="yule" id="newstype">
<input id="datetime_forapp" type="hidden" value="2018-01-10 11:13">
<input id="uid_forapp" type="hidden" value="200000000006590">
<input id="avatar_forapp" type="hidden"
       value="https://00.imgmini.eastday.com/dcminisite/portrait/ec096be6e62e5ebc9f8c265115125e21.jpg">
<input id="nickname_forapp" type="hidden" value="${from}">

<article id="J_article" class="J-article article">

    <div id="title">
        <div class="article-title">
            <h1 class="title">${title}</h1>
        </div>
        <div class="article-src-time">
            <span class="src">${creatTime}&nbsp;&nbsp;&nbsp;&nbsp;${from}</span>
        </div>
    </div>

    <div id="content" class="J-article-content article-content">

    <#list dataset as dataset>
        <#if (dataset.type=="image")>
            <figure class="section img">
              <a class="img-wrap" style="padding-bottom: 66.67%;"
                 data-href="${dataset.content}"
                 data-size="900x600">
                  <img width="100%" alt=""
                       src="${dataset.content}"
                       data-weight="900" data-width="900" data-height="600">
              </a>
            </figure>
        <#elseif (dataset.type=="text")>       <#--注意这里没有返回而是在最后面-->
            <p class="section txt">${dataset.content}</p>
        <#else>
        </#if>
        </#list>
    </div>
</article>

<script src="https://mini.eastday.com/toutiaoh5/js/photoswipe/photoswipe.min.js"></script>
<script src="https://mini.eastday.com/toutiaoh5/js/common.min.js"></script>
<script src="https://mini.eastday.com/toutiaoh5/js/gg_details_v2.min.js"></script>
<script src="https://mini.eastday.com/toutiaoh5/js/page_details_v2.min.js"></script>
</body>
</html>