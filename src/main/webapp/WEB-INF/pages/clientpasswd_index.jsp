<%@ page language="java" import="java.net.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>航空器性能信息维护</title>
<!-- 共用引入方式一 -->
<%@ include file="/common/header.jsp"%>
<!-- 共用引入方式二 -->
<!--<script src="/common/sourceLoader.js" type="text/javascript"></script>-->

<script src="<%=ctx%>/js/test/aircraft_index.js" type="text/javascript"></script>


<style>
td strong {
	color: red;
}

.datatxt_style {
	width: 300px;
}
.td0,.td1,.td2 {
	height: 60px;
}
.formTable{text-align:right;}
.formTable .td0 span{margin-right:10px;}
/* 修改行高  自定义高度*/

.datatxt_style {width: 300px; }
.errortxt input[type=text] {width: 300px; }
</style>
</head>

<body style=" background-image: none; width: auto; min-width: none;">

<div
	style="width:2000px; float: left; margin: 50px 100px 0 100px; background-color: # #DDDDDF;">
<div id="query_title"></div>
<form id="queryFm">
<table cellpadding="0" cellspacing="0" class="datatab formTable"
	style="width: 100%;">
	<tbody>
		<tr>
			<td class="td0"><strong>*</strong>&nbsp;<label for="jxmc"><span>机型名称</span></label></td>
			<td class="td2"><input type="text"
				class="datatxt datatxt_style" title="" name="jxmc" id="jxmc" /></td>
			<td class="td0"><strong>*</strong>&nbsp;<label for="jxdh"><span>机型代号</span></label></td>
			<td class="td2"><input type="text"
				class="datatxt datatxt_style" title="" name="jxdh" id="jxdh" /></td>
			<td class="td0"><strong>*</strong>&nbsp;<label for="jxlxnm"><span>机型类型</span></label></td>
			<td class="td2"><input type="text"
				class="datatxt datatxt_style" title="" name="jxlxnm"
				id="jxlxnm" /></td>
			<td class="td0"><strong>*</strong>&nbsp;<label for="wllxnm"><span>尾流类型</span></label></td>
			<td class="td2"><input type="text"
				class="datatxt datatxt_style" title="" name="wllxnm"
				id="wllxnm" /></td>
		</tr>
		<tr>
			<td class="td0"><strong>*</strong>&nbsp;<label for="fjlsnm"><span>飞机类属</span></label></td>
			<td class="td2"><input type="text"
				class="datatxt datatxt_style" title="" name="fjlsnm"
				id="fjlsnm" /></td>
			<td class="td0"><strong>*</strong>&nbsp;<label for="fdjsl"><span>发动机台数</span></label></td>
			<td class="td2"><input type="text"
				class="datatxt datatxt_style" title="" name="fdjsl"
				id="fdjsl" /></td>
			<td class="td0"><strong>*</strong>&nbsp;<label for="fdjlxnm"><span>发动机类型</span></label></td>
			<td class="td2"><input type="text"
				class="datatxt datatxt_style" title="" name="fdjlxnm"
				id="fdjlxnm" /></td>
			<td class="td0"><strong>*</strong>&nbsp;<label for="effect_date"><span>数据生效日期</span></label></td>
			<td class="td2"><input type="text"
				class="datatxt datatxt_style" title="" name="effect_date"
				id="effect_date"  readonly="readonly" class="Wdate" onclick="WdatePicker()" />
	             </td>
		</tr>
		<tr align="right">
			<td align="right" colspan="8"><input type="button" class="btnsearch"
				name="btnsearch" id="btnsearch" value="查询" onclick="queryTable()" />
			<button type="reset" name="retBt" class="btnreset" id="retBt">重置</button>
			<button type="button" name="select" class="btnnormal" id="select" onclick="getSelected()">获得选中</button>
			<button type="button" name="add" class="btnnormal" id="addBt">添加</button>
			<button type="button" name="update" class="btnnormal" id="updateBt">修改</button>
			</td>
		</tr>
	</tbody>
</table>
</form>
<div id="demo"><!-- 表头,需添加 -->
<div id="query_list_title"></div>
<table cellpadding="0" cellspacing="0" border="0" 
	id="example">
	<thead>
		<tr>
<!-- 			无选择时需要去掉可以没有 -->
			<th>选择</th>
			<th>机型名称</th>
			<th>机型代号</th>
			<th>机型类型</th>
			<th>尾流类型</th>
			<th>飞机类属</th>
			<th>发动机台数</th>
			<th>数据生效日期</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>
</div>
<!-- 修改 -->
<div id="dialog_modal" style="display: none;">
<div id="query_form_title"></div>
<form id="updateFm">
<table cellpadding="0" cellspacing="0" class="datatab formTable">
	<tr >
		<td class="td0"><strong>*</strong>&nbsp;<label for="jxmcUp"><span>机型名称：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="输入3-15个字节之间(中文=2个字节)" name="jxmcUp" id="jxmcUp" /></td>
		<td class="td0"><strong>*</strong>&nbsp;<label for="jxdhUp"><span>机型代号：</span></label>
		<input type="hidden" name="jxnmUp" id="jxnmUp"/></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt  datatxt_style" title="输入字母和数字" name="jxdhUp" id="jxdhUp" /></td>
	</tr>
			
	<tr >
		<td class="td0"><strong>*</strong>&nbsp;<label for="jxlxnmUp"><span>机型类型：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="只能输入数字" name="jxlxnmUp"
			id="jxlxnmUp" /></td>
		<td class="td0"><strong>*</strong>&nbsp;<label for="wllxnmUp"><span>尾流类型：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="只能输入数字" name="wllxnmUp"
			id="wllxnmUp" /></td>
	</tr>	
	<tr >
		<td class="td0"><strong>*</strong>&nbsp;<label for="fjlsnmUp"><span>飞机类属：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="只能输入数字" name="fjlsnmUp"
			id="fjlsnmUp" /></td>
		<td class="td0"><strong>*</strong>&nbsp;<label for="fdjslUp"><span>发动机台数：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="只能输入数字" name="fdjslUp"
			id="fdjslUp" /></td>
	</tr>		
	<tr>
		<td class="td0"><strong>*</strong>&nbsp;<label for="fdjlxnmUp"><span>发动机类型：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="" name="fdjlxnmUp"
			id="fdjlxnmUp" /></td>
		<td class="td0"><strong>*</strong>&nbsp;<label for="effect_dateUp"><span>数据生效日期：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="" name="effect_dateUp"
			id="effect_dateUp"  readonly="readonly" class="Wdate" onclick="WdatePicker()" />
             </td>
	</tr>
	<tr>
		<td class="td0" align="right"><span>附言：</span></td>
			<td colspan="3" align="left">
				<!--多行文本控件  start-->
				<textarea rows="5" cols="50" style="resize:none;"></textarea>
				<!--多行文本控件  end-->
			</td>
	</tr>
	<tr>
			<td colspan="4">
			&nbsp;
			</td>
	</tr>
	<tr align="center">
			<td align="center" colspan="4">
			<button type="reset" name="retBt" class="btnreset" id="retBt1">重置</button>
			<button type="button" name="update" class="btnsave" id="updateBt1" onclick="updateData()">保存</button>
			<button type="reset" name="retBt" class="btnnormal" id="cancelBt1" onclick="closeWin('dialog_modal')">取消</button>
			</td>
		</tr>
</table>
</form>
</div>
<!-- 添加 -->
<div id="dialog_add" style="display: none;">
<div id="add_form_title"></div>
<form id="addFm">
<table cellpadding="0" cellspacing="0" class="datatab formTable">
	<tr >
		<td class="td0"><strong>*</strong>&nbsp;<label for="jxmcAdd"><span>机型名称：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="输入3-15个字节之间(中文=2个字节)" name="jxmcAdd" id="jxmcAdd" /></td>
		<td class="td0"><strong>*</strong>&nbsp;<label for="jxdhAdd"><span>机型代号：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="输入字母和数字" name="jxdhAdd" id="jxdhAdd" /></td>
	</tr>
			
	<tr >
		<td class="td0"><strong>*</strong>&nbsp;<label for="jxlxnmAdd"><span>机型类型：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="只能输入数字" name="jxlxnmAdd"
			id="jxlxnmAdd" /></td>
		<td class="td0"><strong>*</strong>&nbsp;<label for="wllxnmAdd"><span>尾流类型：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="只能输入数字" name="wllxnmAdd"
			id="wllxnmAdd" /></td>
	</tr>	
	<tr >
		<td class="td0"><strong>*</strong>&nbsp;<label for="fjlsnmAdd"><span>飞机类属：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="只能输入数字" name="fjlsnmAdd"
			id="fjlsnmAdd" /></td>
		<td class="td0"><strong>*</strong>&nbsp;<label for="fdjslAdd"><span>发动机台数：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="只能输入数字" name="fdjslAdd"
			id="fdjslAdd" /></td>
	</tr>		
	<tr>
		<td class="td0"><strong>*</strong>&nbsp;<label for="fdjlxnmAdd"><span>发动机类型：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="" name="fdjlxnmAdd"
			id="fdjlxnmAdd" /></td>
		<td class="td0"><strong>*</strong>&nbsp;<label for="effect_dateAdd"><span>数据生效日期：</span></label></td>
		<td class="td2"><input type="text"
			class="datatxt nonullt datatxt_style" title="" name="effect_dateAdd"
			id="effect_dateAdd"  readonly="readonly" class="Wdate" onclick="WdatePicker()" />
             </td>
	</tr>
	<tr>
		<td class="td0" align="right"><span>附言：</span></td>
			<td colspan="3" align="left">
				<!--多行文本控件  start-->
				<textarea rows="5" cols="50" style="resize:none;"></textarea>
				<!--多行文本控件  end-->
			</td>
	</tr>
	<tr>
			<td colspan="4">
			&nbsp;
			</td>
	</tr>
	<tr align="center">
			<td align="center" colspan="4">
			<button type="reset" name="retBt" class="btnreset" id="retBt2">重置</button>
			<button type="button" name="add" class="btnsave" id="addBt1" onclick="addData()">保存</button>
			<button type="reset" name="retBt" class="btnnormal" id="cancelBt1" onclick="closeWin('dialog_add')">取消</button>
			</td>
		</tr>
</table>
</form>
</div>
</body>
</html>