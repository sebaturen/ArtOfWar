<%@include file="includes/globalObject.jsp" %>
<%@ page import ="com.blizzardPanel.gameObject.guilds.GuildActivity" %>
<%@ page import ="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title>${guild.name}</title>
        <%@include file="includes/header.jsp" %>
        <link type="text/css" rel="stylesheet" href="assets/css/index.css">
    </head>
    <body>
        <%@include file="includes/menu.jsp" %>
        <div class="container fill">
            <div id="welcome">
                <div class="row guild_logoName divder">
                    <div class="col-3 log_artofwar">
                        <img src="assets/img/artofwar_logo.png"/>
                    </div>
                    <div class="col-6 align-self-center">
                        <p class='home_name warcraft_font'>${guild.name}</p>
                    </div>
                    <div class="col-3 log_artofwar">
                        <img class='flipImg' src="assets/img/artofwar_logo.png"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8 guild_achievements">
                        <p class='small_title warcraft_font'><fmt:message key="label.activities" /></p>
                        <c:forEach items="${guild.activities}" var="ac">
                            <c:if test="${ac.type == 'CHARACTER_ACHIEVEMENT'}">
                                <div class="new divder row">
                                    <div class="col-2 icon_new"><img src="${ac.characterAchievement.achievement.media.value}"/></div>
                                    <div class="newDetail col-10">
                                        <p class="character-${ac.characterAchievement.characterMember.info.playableClass.id}">
                                            ${ac.characterAchievement.characterMember.name}
                                            <span  class="right_small_date">
                                                <jsp:setProperty name="dateObject" property="time" value="${ac.timestamp}" />
                                                <fmt:formatDate value="${dateObject}" pattern="${general_config.getDateFormat(cookie['locale'].getValue())}" />
                                            </span>
                                        </p>
                                        <p class="desc"><fmt:message key="label.character_achievement" /></p>
                                        <p class="desc">${ac.characterAchievement.achievement.getName(cookie['locale'].getValue())}</p>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${ac.type == 'ENCOUNTER'}">
                                <div class="new divder row">
                                    <div class="col-2 icon_new"><img src="${ac.guildEncounter.encounter.instance.media.value}"/></div>
                                    <div class="newDetail col-10">
                                        <p>
                                                ${ac.guildEncounter.encounter.getName(cookie['locale'].getValue())}
                                            <span  class="right_small_date">
                                                <jsp:setProperty name="dateObject" property="time" value="${ac.timestamp}" />
                                                <fmt:formatDate value="${dateObject}" pattern="${general_config.getDateFormat(cookie['locale'].getValue())}" />
                                            </span>
                                        </p>
                                        <p class="desc"><fmt:message key="label.guild_encounter" /></p>
                                        <p class="desc">${ac.guildEncounter.mode.getName(cookie['locale'].getValue())}</p>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="col-md-4">
                        <p class='small_title warcraft_font'><fmt:message key="label.social_media" /></p>
                        <iframe src="https://discordapp.com/widget?id=200781976653791232&theme=dark" width="100%" height="500" allowtransparency="true" frameborder="0"></iframe>
                        <!-- Content... -->
                    </div>
                </div>
            </div>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>