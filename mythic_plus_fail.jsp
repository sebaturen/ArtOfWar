<%@include file="includes/globalObject.jsp" %>
<% if (!guildMember) {%><%@ page import ="java.net.URLEncoder" %><%
    response.sendRedirect("login.jsp?rdir="+URLEncoder.encode("mythic_plus_fail.jsp", "UTF-8"));
} else {%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title><%= guild_info.getName() %> - Fails Mythic Plus</title>
        <%@include file="includes/header.jsp" %>
        <link type="text/css" rel="stylesheet" href="assets/css/index.css">
        <script src="assets/js/mythicPlus/mythic_plus_fail.js"></script>
    </head>
    <body>
        <%@include file="includes/menu.jsp" %>
        <div class="container fill">
            <div id="afixLoad" class="loader"></div>
            <div id="bestRun" style="display: none;"></div>
            <div id="runList" style="display: none;"></div>
            <div class="item-floting-desc tooltip-affix">
                <div class="itemDesc tooltipDesc">
                    <p id="afix_name"></p>
                    <p id="afix_desc" class="tooltip-yellow itemSpellDetail"></p>
                </div>
            </div>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
<%}%>