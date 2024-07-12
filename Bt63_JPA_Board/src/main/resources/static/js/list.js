$(function(){
    // 페이징 헤더
    $("[name='pageRows']").change(function (){
      //  alert($(this).val()); // 확인용 팝업

        $("[name='frmPageRows']").attr({
            "method": "POST",
            "action": "/board/pageRows",
        }).submit();

    });
});