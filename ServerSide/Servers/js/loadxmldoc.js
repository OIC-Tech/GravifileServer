function loadXMLDoc(url, onreadystatechange) 
{
if (window.XMLHttpRequest)
  {
  xhttp=new XMLHttpRequest();
  }
else
  {
  xhttp=new ActiveXObject("Micro soft.XMLHTTP");
  }
xhttp.onreadystatechange = onreadystatechange;
xhttp.open("GET",url,true);
xhttp.send();
}