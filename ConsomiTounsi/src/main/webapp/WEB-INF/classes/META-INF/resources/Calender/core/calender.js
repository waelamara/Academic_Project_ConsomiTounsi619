window.onload = ()=>{
	let elementCalendrier = document.getElementById("Calender")
	
	let calendrier = new FullCalendar.Calendar(elementCalendrier,{
		plugins:['dayGrid']
	})
	calendrier.render()
}