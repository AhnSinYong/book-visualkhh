// addEventListener()�Լ��� �������� ������ ����
if( ! document.addEventListener ) { return; } 
// Selectors API �� �������� ������ ����
if( ! document.querySelector ) { return; } 
 
// document ������Ʈ�� DOMContentLoaded �̺�Ʈ �����ʸ� ����
document.addEventListener("DOMContentLoaded", function() { 
  // �ؽ�Ʈ �Է� �ʵ��� nput ��� 
  var input = document.querySelector('input[name="txt"]'); 
  // focus �̺�Ʈ �����ʸ� ����
  input.addEventListener("focus", function(event) { 
    var el = event.target; 
    // ĳ���� �ǵڷ� �̵�
    el.selectionStart = el.value.length; 
  }, false); 
  // ĳ���� �ǵڷ� �̵�
  if(input.autofocus == true) { 
    input.selectionStart = input.value.length; 
  } 
}, false);
