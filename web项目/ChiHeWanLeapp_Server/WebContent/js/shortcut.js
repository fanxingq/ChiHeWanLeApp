var arealist = $("#shortcut .w .fl .dropdown");
var myjd = $("#shortcut .w .fr #ttbar-myjd");

var showdroplist = function(name) {
	//$(selector).hover(inFunction,outFunction)
	name.hover(function() {
		name.addClass("hover");
	}, function() {
		name.removeClass("hover");
	});
}

showdroplist(arealist);
showdroplist(myjd);

function test() {
	var fl = document.getElementsByClassName("fl")[0];
	var areaLinks = fl.getElementsByTagName("a");
	var area = fl.getElementsByTagName("span")[0];
	
	for(var i = 0; i < areaLinks.length; i++) {
		areaLinks[i].onclick = function() {
			area.firstChild.nodeValue = getLink(this);
			//alert(areaLinks[i]);
			//alert(this);
			return false;
		}
	}
}

function getLink(link){
	return (link.firstChild.nodeValue);
}

window.onload = test();
