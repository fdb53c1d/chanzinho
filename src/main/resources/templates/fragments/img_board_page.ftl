<main>
<section class="formarea">
<form action="${cwebpath}board.php" method="post" enctype="multipart/form-data">
<input type="hidden" name="board" value="${board.name}">
<input type="hidden" name="replythread" value="0">
<table>
<tr>
<th>Nome</th>
<td><input type="text" name="name" size="28" maxlength="75"></input></td>
</tr>
<tr>
<th>Email</th>
<td><input type="text" name="em" size="28" maxlength="75"></input></td>
</tr>
<tr>
<th>Assunto</th>
<td><input type="text" name="subject" size="35" maxlength="75"></input><input type="submit" value="Abrir Threadzinha"></input></td>
</tr>
<tr>
<th>Mensagem</th>
<td><textarea name="message" cols="48" rows="4"></textarea></td>
</tr>
<tr>
<th>Arquivo</th>
<td><input type="file" name="imagefile" size="35" accept="image/*"></input></td>
</tr>
<tr>
<th>Senha</th>
<td><input type="password" name="postpassword" size="8"></input></td>
</tr>
</table>
</form>
</section>
<section class="postarea">
<#list ops?keys as post>
<article>
<div class="op">
<#if post.fileType != "">
<div class="fileinfo">
<span>Arquivo ${post.file}.${post.fileType} - (${post.fileSizeFormatted}, ${post.imageW}x${post.imageH}, ${post.fileOriginal}.${post.fileType})</span>
</div>
<div class="image">
<figure>
<a target="_blank" href="${cwebpath}${board.name}/src/${post.file}.${post.fileType}">
	<img src="${cwebpath}${board.name}/thumb/${post.file}s.${post.fileType}" alt="${post.id}" class="thumb" height="${post.thumbH}" width="${post.thumbW}">
</a>
<figcaption>
<details>
<summary></summary>
<table class="userdelete">
<tr>
<td>[<input type="checkbox" name="fileonly" id="fileonly" value="on"><label for="fileonly">File Only</label>] <input name="moddelete" onclick="return confirm(_('Are you sure you want to delete these posts?'))" value="Apagar" type="submit"> <input name="modban" value="Ban" onclick="this.form.action='https://salmaochan.org/manage_page.php?action=bans';" type="submit"></td>
</tr>
<tr>
<td>
Report post<br>
Motivo
<input type="text" name="reportreason" size="10" maxlength="255">&nbsp;<input name="reportpost" value="Denunciar" type="submit">
</td>
</tr>
</table>
</details>
</figcaption>
</figure>
</div>
</div>
</#if>
<div class="postinfo">
<span class="subject">${post.subject}</span> <span class="postername"><#if post.name == "">${board.anonymous}<#else>${post.name}</#if></span><span class="tripcode">${post.tripcode}</span> <span class="capcode"></span> <time>${post.timestamp?number_to_datetime}</time> No. ${post.id} [Responder]
</div>
<div class="message">
<p>${post.message}</p>
</div>
</div>
</article>
<#assign replies = ops?api.get(post)>
<#list replies as reply>
<div class="reply">
<div class="postinfo">
<span class="subject">${reply.subject}</span> <span class="postername"><#if reply.name == "">${board.anonymous}<#else>${reply.name}</#if></span><span class="tripcode">${reply.tripcode}</span> <span class="capcode"></span> <time>${reply.timestamp?number_to_datetime}</time> No. ${reply.id} 
<#if reply.fileType != "">
<div class="fileinfo">
<span>Arquivo ${reply.file}.${reply.fileType} - (${reply.fileSizeFormatted}, ${reply.imageW}x${reply.imageH}, ${reply.fileOriginal}.${reply.fileType})</span>
</div>
<div class="image">
<figure>
<a target="_blank" href="${cwebpath}${board.name}/src/${reply.file}.${reply.fileType}">
<img src="${cwebpath}${board.name}/thumb/${reply.file}s.${reply.fileType}" alt="${reply.id}" class="thumb" height="${reply.thumbH}" width="${reply.thumbW}">
</a>
<figcaption>
<details>
<summary></summary>
<table class="userdelete">
<tr>
<td>[<input type="checkbox" name="fileonly" id="fileonly" value="on"><label for="fileonly">File Only</label>] <input name="moddelete" onclick="return confirm(_('Are you sure you want to delete these posts?'))" value="Apagar" type="submit"> <input name="modban" value="Ban" onclick="this.form.action='https://salmaochan.org/manage_page.php?action=bans';" type="submit"></td>
</tr>
<tr>
<td>
Report post<br>
Motivo
<input type="text" name="reportreason" size="10" maxlength="255">&nbsp;<input name="reportpost" value="Denunciar" type="submit">
</td>
</tr>
</table>
</details>
</figcaption>
</figure>
</div>
</#if>
</div>
<div class="message">
<p>${reply.message}</p>
</div>
</div>
</#list>
<hr>
</#list>
</section>
</main>
