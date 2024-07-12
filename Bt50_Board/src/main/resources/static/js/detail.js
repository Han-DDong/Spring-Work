$(function(){
    // 글 삭제 버튼
    $("#btnDel").click(function () {
        let answer = confirm("삭제하시겠습니까?");
        answer && $("form[name='frmDelete']").submit();
    });

    // 현재 글의 id 값
    const id = $("input[name='id']").val().trim();

    // 현재 글의 댓글들을 불러온다
    loadComment(id);


    // 댓글 작성 버튼 누르면 댓글 등록 하기.
    // 1. 어느글에 대한 댓글인지? --> 위에 id 변수에 담겨있다
    // 2. 어느 사용자가 작성한 댓글인지? --> logged_id 값
    // 3. 댓글 내용은 무엇인지?  --> 아래 content
    $("#btn_comment").click(function (){
        const content = $("#input_comment").val().trim();

        // 검증
        if (!content) {
            alert("댓글을 입력을 하세요");
            $("#input_comment").focus();
            return;
        }

        // POST 방식으로 전달해 줄 parameter 준비
        const data = {
          "post_id": id,
          "user_id": logged_id,
          "content": content,
        };

        $.ajax({
            url: "/comment/write",
            type: "POST",
            data: data,
            cache: false,
            success: function (data, status) {
                if (status == "success") {
                    if (data.status !== "OK") {
                        alert(data.status);
                        return;
                    }
                    loadComment(id); // 댓글목록 다시 업데이트!
                    $("#input_comment").val('');
                }
            },

        });

    });

});


// 특정 글(post_id) 의 댓글 목록 읽어오기
function loadComment(post_id) {

    $.ajax({
        url: "/comment/list/" + post_id,
        cache: false,
        success: function (data, status) {
            if (status == "success") {
                // 서버쪽 에러 메시지 있는경우
                if (data.status != "OK") {
                    alert(data.status)
                    return;
                }
                buildComment(data); // 댓글 화면 렌더링

                // ⭐️댓글목록을 불러오고 난뒤에 삭제에 대한 이벤트 리스너를 등록해야 한다
                addDelete();
            }
        },
    });

}


function buildComment(result) {
   $("#cmt_cnt").text(result.count); // 댓글 총 개수

    const out = [];
   result.data.forEach(comment => {
       let id = comment.id;
       let content = comment.content.trim();
       let regdate = comment.regdate;

       let user_id = parseInt(comment.user.id);
       let username = comment.user.username;
       let name = comment.user.name;

       // 삭제버튼 여부
       // 댓글작성 아이디가 아니라면 삭제버튼 없음
       const delBtn = (logged_id !== user_id) ? '' : `
            <i class="btn fa-solid fa-delete-left text-danger" data-bs-toggle="tooltip" data-cmtdel-id="${id}" title="삭제"></i>
       `;

       const row = `
            <tr>
                <td><span><strong>${username}</strong><br><small class="text-secondary">(${name})</small></span></td>
                <td>
                    <span>${content}</span>${delBtn} <!-- 댓글과 댓글 작성 아이디가 일치하면 삭제버튼 생김 -->        
                </td>
                <td><span><small class="text-secondary">${regdate}</small></span></td>
            </tr>
        `;
       out.push(row);
   });

   $("#cmt_list").html(out.join('\n'));
}


// 댓글삭제 버튼을 누르면 해당 댓글 삭제하는 이벤트 등록
function addDelete(){

    // 현재 글의 id
    const id = $("input[name='id']").val().trim();

    $("[data-cmtdel-id]").click(function (){

        if (!confirm("댓글을 삭제하시겠습니까?")) {
            return;
        }

        // 삭제 할 댓글의 id
        // 여기서 this => 삭제버튼
        const comment_id = $(this).attr("data-cmtdel-id");

        $.ajax({
            url: "/comment/delete",
            type: "POST",
            cache: false,
            data: {"id": comment_id},
            success: function (data, status) {
                if (status == "success") {
                    if (data.status !== "OK") {
                        alert(data.status);
                        return;
                    }

                    // 삭제 후 댓글 목록 업데이트
                    loadComment(id);
                }
            }
        });

    });
}

































