<section class="bottom">
<table>
<tr>
<td>Anterior</td>
<td class="pagelist">
<ul>
<#list 1..10 as pageNumber>
<li>${pageNumber}</li>
</#list>
</ul>
</td>
<td><input type="submit" value="Próximo"></input></td>
<td>Catálogo</td>
</tr>
</table>
</section>
<nav class="navbottom">
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
<footer>
<h4>bla bla bla</h4	>
<p>ksdlajbgaçkj arkçwjs bvçgisnbfçionrsklgaerçslkgvn</p>
</footer>