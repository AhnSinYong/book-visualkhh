// progress ��� 
var progress = null; 
// button ��� 
var button = null; 
// �ε��� ������ ��ü ũ��
var total = undefined; 
// �ε尡 ���� ������ ������ ũ��
var loaded = undefined; 
 
// document ������Ʈ�� DOMContentLoaded �̺�Ʈ�� �����ʸ� ����
document.addEventListener("DOMContentLoaded", function() { 
  progress = document.querySelector('progress'); 
  button = document.querySelector('button'); 
  button.addEventListener("click", data_load, false); 
}, false); 
 
// �ε� ����
function data_load() { 
  // ��ư�� ��ȿȭ �ϰ� ǥ�ø� ����
  button.disabled = true; 
  button.textContent = '�ε���'; 
  // XmlHttpRequest 
  var xhr = new XMLHttpRequest(); 
  // progress �̺�Ʈ�� �ڵ鷯�� ���� 
  xhr.onprogress = progress_handler; 
  // load �̺�Ʈ�� �ڵ鷯�� ����
  xhr.onload = load_handler; 
  // ������ �ε�
  xhr.open("GET", "aaa.zip"); 
  xhr.send(); 
} 
 
// progress �� �ڵ鷯
function progress_handler(event) { 
  // �ε��� ������ ��ü ũ��
  total = event.total;   
  // �ε尡 �Ϸ�� ������ ��ü ũ��
  loaded = event.loaded; 
  // ���α׷����ٸ� ������Ʈ
  update_progress(); 
} 
 
// load �̺�Ʈ�� �ڵ鷯
function load_handler(event) { 
  // �ε尡 �Ϸ�� ũ�⸦ ������ ���� ũ�⿡ �����
  loaded = total; 
  // ���α׷����ٸ� ������Ʈ
  update_progress(); 
  // ��ư�� ǥ�ø� ����
  button.textContent = '�ε� �Ϸ�'; 
} 
 
// ���α׷����ٸ� ������Ʈ
function update_progress() { 
  if( isNaN(total) || isNaN(loaded) ) { return; } 
  if( ! progress ) { return; } 
  // progress ����� max �� value �Ӽ��� ���� ����
  progress.max = total; 
  progress.value = loaded; 
  // ������ ���� ���ϱ�
  rate = progress.position; 
  // progress ��Ҹ� �������� �ʴ� ��������
  if( ! rate && total > 0 ) { 
    rate = loaded / total; 
  } 
  progress.textContent = parseInt( rate * 100 ) + '% (' + loaded + '/' + total + ')'; 
}
