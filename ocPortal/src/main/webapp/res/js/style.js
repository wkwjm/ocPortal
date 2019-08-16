
$(function(){
  //实现 章节鼠标焦点 动态效果
  $('.chapter li').hover(function(){
    $(this).find('.icon-video').css('color','#FFF');
  },function(){
    $(this).find('.icon-video').css('color','#777');
  });

  if(SHIRO_LOGIN){
    //判断是否已经收藏
    var courseId = ${(course.id)!}
    if(courseId){
      var url = '${s.base}/collections/isCollection.html';
      doCollect(courseId,url);
    }
    //判断是否已经关注教师
    var followId = ${(courseTeacher.id!)};
    if(followId){
      var url = '${s.base}/follow/isFollow.html';
      doFollow(followId,url);
    }
  }

});

function display(){
  var display1 = $(this).parent().find('ul').css('display');
  console.log(display1);
  if(display == 'none'){
    $(this).parent().find('ul').css('display','block');
    $(this).find('.drop-down').html('▼');
  }else{
    $(this).parent().find('ul').css('display','none');
    $(this).find('.drop-down').html('▲');
  }
};
/**
 *展示tab commentQA
 **/
function showTab(el,divId,type){

  $('.course-menu').find('span').each(function(i,item){
    $(item).removeClass('cur');
  });
  $(el).addClass('cur');

  if(divId == 'courseSection'){
    $('#courseSection').show();
    $('#commentQA').hide();
  }else {
    $('#commentQA').show();
    $('#courseSection').hide();
    _queryPage(1,type);//默认加载 第 1 页
  }
}

/**
 *加载 课程评论 & 问答
 * courseId：课程id
 * sectionId: 章节id
 * type : 类型 0-评论；1-答疑
 */
var _type = 0; //全局变量
function _queryPage(pageNum,type){
  if(type == undefined)
    type = _type;
  else
    _type = type;
  //加载评论或者QA
  if(pageNum == undefined)
    pageNum = 1;
  var courseId = ${(course.id)!};//课程id
  var url = '${s.base}/courseComment/segment.html';
  $("#commentQA").load(
      url,
      {'courseId':courseId,'type':type,'pageNum':pageNum},
      function(){}
  );
};

//收藏
function doCollect(courseId,url){
  if(url == undefined){
    url = '${s.base}/collections/doCollection.html';
  }
  //处理收藏
  $.ajax({
    url:url,
    type:'POST',
    dataType:'json',
    data:{"courseId":courseId},
    success:function(resp){
      if(resp.errcode == 1){//已收藏
        $('#collectionSpan').attr('class','followed');
      }else if(resp.errcode == 0){//未收藏
        $('#collectionSpan').attr('class','following');
      }
    }
  });
}

//关注
function doFollow(followId,url){
  if(url == undefined){
    url = '${s.base}/follow/doFollow.html';
  }
  $.ajax({
    url:url,
    type:'POST',
    dataType:'json',
    data:{"followId":followId},
    success:function(resp){
      if(resp.errcode == 1){
        $('#followSpan').html('已关注');
      }else if(resp.errcode == 0){
        $('#followSpan').html('+关注');
      }
    }
  });
}

