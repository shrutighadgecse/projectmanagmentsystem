const tabOrder = ["po", "sales", "design", "qa" ,"ppc","cp-cell","stores","assembly1","assembly2","dispatch"];

function showTab(tabId) {
  const activeIndex = tabOrder.indexOf(tabId);
  if (activeIndex === -1) return;

  document.querySelectorAll(".tab-content").forEach(t => t.classList.remove("active"));
  document.getElementById(tabId)?.classList.add("active");

  document.querySelectorAll(".tab").forEach(btn => btn.classList.remove("active"));
  document.querySelector(`[onclick="showTab('${tabId}')"]`)?.classList.add("active");

  const progressLine = document.querySelector(".progress-line");
  if (progressLine) {
    const percent = (activeIndex / (tabOrder.length - 1)) * 100;
    progressLine.style.setProperty("--progress", `${percent}%`);
  }

  document.querySelectorAll(".progress-step").forEach((step, idx) => {
    step.classList.toggle("active", idx === activeIndex);
    step.classList.toggle("completed", idx < activeIndex);
  });
}

// Attach click handlers to the progress steps so the flow is consistent
document.querySelectorAll(".progress-step").forEach(step => {
  step.addEventListener("click", () => {
    const stepKey = step.dataset.step;
    if (stepKey) showTab(stepKey);
  });
});

// Initialize the UI state on load
showTab("po");

