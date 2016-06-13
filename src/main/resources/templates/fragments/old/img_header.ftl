<script type="text/javascript" src="${cwebpath}lib/javascript/protoaculous-compressed.js"></script>
<link rel="stylesheet" type="text/css" href="${cwebpath}css/img_global.css" />
<#list styles as style>
	<lin rel="<#if style != defaultStyle>alternate </#if>stylesheet" type="text/css" href="${cwebpath}css/${style}.css" title="${style}" />
</#list>
<script type="text/javascript"><!--
		var ku_boardspath = '${BOARDSPATH}';
		var ku_cgipath = '${CGIPATH}';
		var style_cookie = "kustyle";
<#if replyThread gt 0>
		var ispage = false;
<#else>
		var ispage = true;
</#if>
//--></script>
<script type="text/javascript" src="${WEBPATH}/lib/javascript/kusaba.js"></script>
<script type="text/javascript"><!--
	var hiddenthreads = getCookie('hiddenthreads').split('!');
//--></script>
<#if board.enableCaptcha == 1>
	<script type="text/javascript> var RecaptchaOptions = { theme : 'clean' }; </script>
</#if>
</head>
<body>
<div class="adminbar">
<#if STYLESWITCHER>
	<#if DROPSWITCHER>
		<select onchange="javascript:if(selectedIndex != 0)set_stylesheet(options[selectedIndex].value);return false;">
			<option>{t}Styles{/t}</option>
		<#list styles as style>
			<option value="${style}">${style}</option>
		</#list>
		</select>
	<#else>
		<#list styles as style>
			[<a href="#" onclick="javascript:set_stylesheet('${style}');return false;">${style}</a>]&nbsp;
		</#list>
	</#if>
	<#if styles?size gt 0>
		-&nbsp;
	</#if>
</#if>
<#if WATCHTHREADS>
	[<a href="#" onclick="javascript:showwatchedthreads();return false" title="{t}Watched Threads{/t}">WT</a>]&nbsp;
</#if>
[<a href="${WEBPATH}" target="_top">{t}Home{/t}</a>]&nbsp;[<a href="${CGIPATH}/manage.php" target="_top">{t}Manage{/t}</a>]
</div>
<div class="navbar">
<#if GENERATEBOARDLIST>
	<#list boardlist as section>
		[
	<#list section as board>
		<a title="${board.desc}" href="${BOARDSFOLDER}${board.name}/">${board.name}</a><#if board?has_next> / </#if>
	</#list>
		]
	</#list>
<#else>
	<#if is_file(boardlist)>
		<#include boardlist>
	</#if>
</#if>
</div>
<#if WATCHTHREADS && !isoekaki && !hidewatchedthreads>
				<script type="text/javascript"><!--
				if (getCookie('showwatchedthreads') == '1') {
				document.write('<div id="watchedthreads" style="top: ${ad_top}px; left: 25px;" class="watchedthreads"><div class="postblock" id="watchedthreadsdraghandle" style="width: 100%;">{t}Watched Threads{/t}<\/div><span id="watchedthreadlist"><\/span><div id="watchedthreadsbuttons"><a href="#" onclick="javascript:hidewatchedthreads();return false;" title="{t}Hide the watched threads box{/t}"><img src="${cwebpath}css/icons/blank.gif" border="0" class="hidewatchedthreads" alt="hide" /><\/a>&nbsp;<a href="#" onclick="javascript:getwatchedthreads(\'0\', \'${board.name}\');return false;" title="{t}Refresh watched threads{/t}"><img src="${cwebpath}css/icons/blank.gif" border="0" class="refreshwatchedthreads" alt="refresh" /><\/a><\/div><\/div>');
				watchedthreadselement = document.getElementById('watchedthreads');
				watchedthreadselement.style.top = getCookie('watchedthreadstop');
				watchedthreadselement.style.left = getCookie('watchedthreadsleft');
				watchedthreadselement.style.width = Math.max(250,getCookie('watchedthreadswidth')) + 'px';
				watchedthreadselement.style.height = Math.max(75,getCookie('watchedthreadsheight')) + 'px';
				getwatchedthreads('<!sm_threadid>', '${board.name}');
			}
			//--></script>
</#if>

<div class="logo">
<#if HEADERURL != '' && board.image == ''>
	<img src="${HEADERURL}" alt="{t}Logo{/t}" /><br />
<#elseif board.image != '' && board.image != "none">
	<img src="${board.image}" alt="{t}Logo{/t}" /><br />
</#if>
<#if DIRTITLE>
	/${board.name}/ - 
</#if>
${board.desc}</div>
${board.includeHeader}
<hr />
