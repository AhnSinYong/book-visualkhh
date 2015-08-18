// addEventListener()�Լ��� �������� ������ ���� 
if( ! document.addEventListener ) { return; } 
// Selectors API �� �������� ������ ����
if( ! document.querySelector ) { return; } 
 
// document ������Ʈ�� DOMContentLoaded �̺�Ʈ�� �����ʸ� ����
document.addEventListener("DOMContentLoaded", function() { 
  // ��ư�� input ��� 
  var btn = document.querySelector('input[type="button"]'); 
  // ��ư�� click �̺�Ʈ �����ʸ� ����
  btn.addEventListener("click", text_select, false); 
}, false); 
 
// �ؽ�Ʈ �Է��ʵ忡 ��Ŀ�� �Ǿ������� ó��
function text_select(event) { 
  // �ؽ�Ʈ �Է� �ʵ��� input ���
  var input = document.querySelector('input[type="text"]'); 
  // �Էµ� ���� ��θ� ���� ���·�
  input.select(); 
}
