<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<style type="text/css">
		body {
			margin: 0;
			padding: 0;
		}
		h2 {

		}
	</style>
</head>
<body>
	<div>
		<h2>1+1=?</h2>
	</div>
	<script type="text/javascript">
		function createRequest () {
			let xhr = null;
			try {
				xhr = new XMLHttpRequest();
			} catch (tryMS) {
				try {
					xhr = new ActiveXObject("Msxm12.XMLHTTP");
				} catch (otherMS) {
					try {
						xhr = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (failed) {
						xhr = null;
					}
				}
			}
			return xhr;
		}

		let data = {
			a: 1,
			b: 1,
		};


		let xhr = createRequest();
		xhr.open('GET', `http://localhost:8080/springMvcDemo1/log1?userAccount=liuyuhang&userPass=dashabi`);
		xhr.send();
		xhr.onreadystatechange = () => {
			console.log(xhr.status);
			if (xhr.readyState == 4 && xhr.status == 200) {
				alert(xhr.responseText);
			}
		}
	</script>
</body>
</html>