// addEventListener()�Լ��� �������� ������ ���� 
if( ! document.addEventListener ) { return; } 
// Selectors API �� ���� ���� ������ ����
if( ! document.querySelector ) { return; } 
 
// document ������Ʈ�� DOMContentLoaded �̺�Ʈ �����ʸ� ����
document.addEventListener("DOMContentLoaded", function() { 
  // ��ư�� click �̺�Ʈ �����ʸ� ����
  var btn = document.querySelector('input[type="button"]'); 
  btn.addEventListener("click", check, false); 
}, false); 
 
// �����ܾ� Ȯ��
function check() { 
  // textarea ��� 
  var textarea = document.querySelector('textarea[name="ad"]'); 
  // ���� �ܾ� �˻� 
  var li_list = document.querySelectorAll('ul#ngwords>li'); 
  for( var i=0; i<li_list.length; i++ ) { 
    // ���� �ܾ�
    var ngword = li_list.item(i).textContent; 
    if( ! ngword ) { continue; } 
    // ���� �ܾ��� ���縦 üũ 
    var offset = textarea.value.indexOf(ngword); 
    if(offset == -1) { continue; } 
    // �����ܾ� �κ��� ���û��·� ����
    textarea.setSelectionRange(offset, offset + ngword.length); 
    break; 
  } 
}
