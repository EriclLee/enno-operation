<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: EriclLee
  Date: 15/11/18
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>

<div id="windows-overlay" class="window-overlay">
  <div id="modal" class="modal"
       style="width: 800px; height: auto; margin-left:-400px; margin-top: -168px; top:50%; left:50%; z-index: 1000; ">
    <div class="modal-header" style="cursor: move;"><h4>Add Event Source<a href="javascript:void(0);" onclick="closeDialog()" class="close"><span
            class="icon-close icon-Large"></span></a></h4></div>
    <div class="modal-content" id="">
      <form id="form" class="form form-horizontal" method="post" action="/eventsources/add">
        <fieldset>
          <legend>add event source</legend>
          <div class="item">
            <div class="control-label">Event Source Name</div>
            <div class="controls">
              <div class="control-text">
                <input type="hidden" name="subscriberId" value="${Subscriber.id}" />
                ${Subscriber.name}
              </div>
            </div>
          </div>
          <div class="item">
            <div class="control-label">Template</div>
            <div class="controls">
              <div class="select-con">
                <select id="subscriberSelect" name="eventSourceId" class="dropdown-select">
                  <option value="-1" selected="selected">----Please Selecte Template----</option>
                  <c:forEach items="${EventSourceList}" var="est">
                    <option value="${est.id}">${est.sourceId}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          <div class="form-actions">
            <input id="submit" class="btn btn-primary" type="button" value="Submit" onclick="submitForm('form','/eventsources/addSubscriber')"/>
            <input class="btn btn-cancel" type="button" value="Cancel" onclick="closeDialog()"/>
          </div>
        </fieldset>
      </form>
    </div>
    <div class="modal-footer"></div>
  </div>
</div>