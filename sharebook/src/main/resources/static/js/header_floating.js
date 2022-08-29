import {
	computePosition,
	flip,
	shift,
	offset ,
} from 'https://cdn.skypack.dev/@floating-ui/dom@0.2.0';

const button = document.querySelector('#button');
const tooltip = document.querySelector('#tooltip');

function setUpTooltip() {
	computePosition(button, tooltip,  {
		placement: 'bottom',
		middleware: [offset(6), flip(), shift ({ padding : 5 })],
	}).then(( { x, y }) => {
		Object.assign(tooltip.style, {
			left: `${x}px`,
			top: `${y}px`,
		});
	});
}

function update() {
	computePosition(button, tooltip, {
		
	}).then(({x, y, placement, middlewareData}) => {
		
	});
}

function showTooltip() {
	tooltip.style.display = "block";
	setUpTooltip();
}

function hideTooltip() {
	tooltip.style.display = "none";
}

[
	["click", showTooltip],
].forEach(([event, listener]) => {
	button.addEventListener(event, listener);
});