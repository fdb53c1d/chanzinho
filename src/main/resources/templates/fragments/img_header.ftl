<link rel="stylesheet" type="text/css" href="${cwebpath}css/img_global.css" />
</head>
<body>
<nav class="navtop">
<#list boardList?keys as section>
<#if boardList?api.get(section)?size gt 0>
<ul>
<#list boardList?api.get(section) as board>
<li>${board.name}</li>
</#list>
</ul>
</#if>
</#list>
</nav>
<header>
<img src="assets/favicon.png" />
<h2>/${board.name}/ - ${board.desc}</h2>
</header>