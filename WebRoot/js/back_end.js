//sign in
//--------------------------------------------------------------

//��¼��½ͨ��
var channel = null;
var el = null;

function chooseChannel(c, e) {
	channel = c;
	if (el != null) {
		el.style.backgroundColor = "#4c3d7a";
	}
	e.style.backgroundColor = "#352a54";
	el = e;

	var b = document.getElementById("mSign_in");
	b.disabled = false;
	b.innerHTML = "Sign In";
	b.style.backgroundColor = "#4c3d7a";
}

function signIn() {
	var form = document.getElementById("sign_in");
	form.action = "hr/SignInBE?channel=" + channel;
	form.submit();
}

// UI
// --------------------------------------------------------------
// �洰�ڸı�߶�
function panelHeight(i) {
	var bodyHeight = document.documentElement.clientHeight;
	var paneldiv = document.getElementById("panel");
	var iframediv = document.getElementById("member_list");
	var paperdiv = document.getElementById("paper");
	if (bodyHeight > 650) {
		paneldiv.style.height = (bodyHeight - 102) + "px";
		if (i == 1) {
			iframediv.style.height = (bodyHeight - 155) + "px";
			paperdiv.style.height = (bodyHeight - 175) + "px";
		} else {
			iframediv.style.height = (bodyHeight - 150) + "px";
			paperdiv.style.height = (bodyHeight - 170) + "px";
		}
	}
}

// ��ʾ��Ŀ��ϸ
var dPanel = null;
function showDetails(i) {
	if (dPanel != null) {
		dPanel.style.visibility = "hidden";
	}
	dPanel = top.document.getElementById("dPanel" + i);
	dPanel.style.visibility = "visible";
}
