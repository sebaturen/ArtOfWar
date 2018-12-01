<%@include file="../includes/globalObject.jsp" %>
<%
if(user.getGuildRank() != 0 && user.getGuildRank() != 1)
{//Validate user is Guild Lider or Officer %><%@ page import ="java.net.URLEncoder" %><%
    response.sendRedirect("login.jsp?rdir="+URLEncoder.encode("userpanel/settings.jsp", "UTF-8"));
}
else
{%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title><%= guild_info.getName() %> - Settings panel</title>
        <%@include file="../includes/header.jsp" %>
    </head>
    <body>
        <%@include file="../includes/menu.jsp" %>
        <div class="container fill">
            <p>Update all page information (re-load all from Blizzard)</p>
            <a href='update/update_panel.jsp'>
                <button type='submit' class='btn btn-outline-warning btn-sm'>Force the Update</button>
            </a>
            <br><br>
            <p>Setting Guild Ranks</p>
            <a href='guildRank/guild_rank.jsp'>
                <button type='submit' class='btn btn-outline-warning btn-sm'>Guild Rank</button>
            </a>
        </div>
    </body>
</html>
<%}%>