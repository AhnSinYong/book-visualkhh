document.addEventListener("DOMContentLoaded", function() { 
  // �� ��ȣ 
  var no = 0; 
  // document ������Ʈ�� keyup �̺�Ʈ �����ʸ� ����
  document.addEventListener("keyup", function(event) { 
    // ������ �� ��ȣ
    var m = window.location.hash.match(/^c(\d+)$/); 
    if( m ) { 
      no = parseInt(m[1]); 
    } 
    // ���� Ű�� �ڵ�
    var code = event.keyCode; 
    // ��,�� ȭ��ǥ�� �ƴϸ� ����
    if( code != 37 && code != 39 ) { return; } 
    // ���� Ű�� ���� ���� �� ��ȣ�� ����
    var next = no; 
    if( code == 37 ) { 
      // ���� ȭ��ǥ�� ���� ���
      next --; 
    } else if( code == 39 ) { 
      // ������ ȭ��ǥ�� ���� ��� 
      next ++; 
    } 
    // ���� �� ��ȣ�� section���
    var section = document.querySelector('#c' + next); 
    //���� �� ��ȣ�� section��Ұ� ������ �÷��� �ĺ��ڸ� ���� 
    if( section ) { 
      window.location.hash = "#c" + next; 
      no = next; 
    } 
  }, false); 
}, false);
