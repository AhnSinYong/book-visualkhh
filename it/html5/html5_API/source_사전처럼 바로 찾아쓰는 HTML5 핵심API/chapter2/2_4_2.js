// addEventListener()�Լ��� �������� ������ ����
if( ! document.addEventListener ) { return; } 
// Selectors API �� �������������� ����
if( ! document.querySelector ) { return; } 
 
// document ������Ʈ�� DOMContentLoaded �̺�Ʈ �����ʸ� ����
document.addEventListener("DOMContentLoaded", function() { 
  // �ؽ�Ʈ �Է� �ʵ���  input ��� 
  var input = document.querySelector('input[name="txt"]'); 
  // select �̺�Ʈ �����ʸ� ����
  input.addEventListener("select", function(event) { 
    var el = event.target; 
    var stxt = el.value.substring(el.selectionStart, el.selectionEnd); 
    alert(stxt); 
  }, false); 
}, false);
