function valForm() {
	if (document.dues.flatNo.value == "select") {
		alert("Select Flat No.");
		document.dues.flatNo.focus();
		return false;
	}
	if (document.dues.duesType.value == "select") {
		alert("Select a Dues Type");
		document.dues.duesType.focus();
		return false;
	}
	if (document.dues.date.value == "") {
		alert("Enter the date");
		document.dues.date.focus();
		return false;
	}
	if (document.dues.payable.value == "") {
		alert("Enter the Amount");
		document.dues.payable.focus();
		return false;
	}
	document.dues.submit();
}


function sendFlId() {
	var flId = document.dues.flatNo.value;
	var url = "TotalDues?flId=" + flId;
	if (window.XMLHttpRequest) {
		request = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	try {
		request.onreadystatechange = getTotal;
		request.open("GET", url, true);
		request.send();
	} catch (e) {
		alert("Unable to connect to server");
	}
}

function getTotal() {
	if (request.readyState == 4) {
		var total = request.responseText;
		document.getElementById('amount').value=total;
}
}