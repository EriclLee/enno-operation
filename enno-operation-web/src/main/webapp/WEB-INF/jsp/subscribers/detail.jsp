<%--
  Created by IntelliJ IDEA.
  User: EriclLee
  Date: 15/11/17
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class="overview">
  <div class="topbar">
    <div>
      <div class="breadcrumbs">
        <a class="level level-zone level-zone-2" href="#subscribers/list?pageIndex=1&pageSize=10" data-permalink="">Subscribers</a>
        <a class="level" href="#" data-permalink="">${Subscriber.name}</a>
      </div></div>
  </div>
  <div class="overview-main" style="width: 35%">
    <div class="description" style="width: 100%">
      <div class="detail-item">
        <div class="title"><h3>Event Soure</h3>

          <div class="dropdown"><input class="dropdown-toggle" type="text">

            <div class="dropdown-text"><span class="icon-menu"></span></div>
            <div class="dropdown-content dropdown-wide">
              <a class="btn" href="javascript:void(0);" onclick="getAddES2SubscriberForm('${Subscriber.id}')"><span class="icon icon-resize"></span><span class="text">Add EventSource</span></a>
              <a class="btn" href="javascript:void(0);" onclick="getEditSubscriberForm('${Subscriber.id}')"><span class="icon icon-resize"></span><span class="text">Edit</span></a>
              <a class="btn" href="javascript:void(0);" onclick="offlineSubscriber('${Subscriber.id}')"><span class="icon icon-keypairs"></span><span class="text">Offline</span></a>
              <a class="btn" href="javascript:void(0);" onclick="deleteSubscriber('${Subscriber.id}')"><span class="icon icon-keypairs"></span><span class="text">Delete</span></a>
            </div>
          </div>
        </div>
        <dl class="dl-horizontal">
          <dt>Subscriber Name</dt>
          <dd>${Subscriber.name}</dd>
          <dt>Status</dt>
          <dd>${Subscriber.state}</dd>
          <dt>Event Sources</dt>
          <dd>
            <c:forEach items="${Subscriber.eventsourceList}" var="es">
              ${es.sourceId}
              <a href="javascript:void(0);" onclick="removeSubscriberFromES('${es.id}','${Subscriber.id}')"><span>[DELETE]</span></a>
              <br/>
            </c:forEach>
          </dd>
          <dt>Address</dt>
          <dd>${Subcsriber.address}</dd>
          <dt>Create Time</dt>
          <dd>${EventSource.createTime}</dd>
          <dt>Update Time</dt>
          <dd>${EventSource.updateTime}</dd>
          <dt>Comments</dt>
          <dd>${EventSource.comments}</dd>
        </dl>
      </div>
    </div>
  </div>
  <div class="overview-activities" style="width: 63%">
    <div class="activities-inner">
      <h4 class="activities-title">
        Event Log
        <%--<a href="#" >All</a>--%>
      </h4>
      <ol class="activities-items">
        <c:forEach items="${EventLogList}" var="el">
          <li>
            <c:if test="${el.level=='Error'}">
              <span class="job-status failed"></span>
            </c:if>
            <c:if test="${el.level=='Alert'}">
              <span class="job-status working"></span>
            </c:if>
            <c:if test="${el.level=='Info'}">
              <span class="job-status successful"></span>
            </c:if>
            <div class="job-details">
              <a class="job-action" href="#">${el.title}</a>
              <span class="consumed"></span>
              <span class="job-time">2015-11-28 10:28:43</span>
              <ul class="resources">
                <li>Event Source: ${el.eventSourceModel.sourceId}</li>
                <li>Subscriber: ${el.subscriberModel.name}</li>
                <li>${el.message}</li>
              </ul>
            </div>
          </li>
        </c:forEach>
      </ol>
    </div>
  </div>
</div>
