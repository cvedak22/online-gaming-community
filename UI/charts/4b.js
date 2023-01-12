const generate4b = async () => {
  data = await fetch("http://localhost:8080/getFrequentPosterComments")
    .then((response) => response.json())
    .then((data) => {
      return data;
    });

  // x--> poster name
  // y--> commenters distance

  var xyValues = [];  
  data.forEach((item) => {    
    var obj = {};
    if (
      !xyValues.filter(
        (e) =>
          e.x ===
            [...new Set(data.map((item) => item.posterName))]
              .sort()
              .findIndex((x) => x === item.posterName) &&
          e.y === item.distanceFromPoster * 0.000621371192
      ).length > 0
    ) {
      obj["x"] = [...new Set(data.map((item) => item.posterName))]
        .sort()
        .findIndex((x) => x === item.posterName);
      obj["y"] = item.distanceFromPoster * 0.000621371192;

      xyValues.push(obj);
    }
  });

  new Chart("4bChart", {
    type: "scatter",
    data: {
      labels: [...new Set(data.map((item) => item.posterName))].sort(),
      datasets: [
        {
          backgroundColor: "blue",
          data: xyValues,
        },
      ],
    },
    options: {
      scales: {
        xAxes: [
          {
            ticks: {
              userCallback: function(label, index, labels) {
                return [
                  ...new Set(data.map((item) => item.posterName)),
                ].sort()[index];
              },
            },
          },
        ],
      },
      legend: { display: false },
      title: {
        display: true,
        text: "4(b) - Posters and Distances from their Frequent Commenters",
      },
    },
  });
};

generate4b();
