const generate4a = async () => {
  data = await fetch("http://localhost:8080/getFrequentPosterComments")
    .then((response) => response.json())
    .then((data) => {
      return data;
    });

  // x--> poster_name
  // y--> number of distinct commenters

  const xyValues = Object.values(
    data.reduce((obj, item) => {
      obj[item.posterName] = obj[item.posterName] || {
        posterName: item.posterName,
        commenter: [],
        numberOfDistinctCommenters: 0,
      };
      if (!obj[item.posterName].commenter.includes(item.frequentCommenter)) {
        obj[item.posterName].commenter.push(item.frequentCommenter);
        obj[item.posterName].numberOfDistinctCommenters++;
      }
      return obj;
    }, {})
  )
    .map(function(item) {
      delete item.commenter;
      return item;
    })
    .sort(function(a, b) {
      return b.numberOfDistinctCommenters - a.numberOfDistinctCommenters;
    });

  new Chart("4aChart", {
    type: "bar",
    data: {
      labels: xyValues.map((a) => a.posterName),
      datasets: [
        {
          backgroundColor: "blue",
          data: xyValues.map((a) => a.numberOfDistinctCommenters),
        },
      ],
    },
    options: {
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
              callback: function(value) {
                if (value % 1 === 0) {
                  return value;
                }
              },
            },
          },
        ],
      },
      legend: { display: false },
      title: {
        display: true,
        text:
          "4(a) - Posters and number of corresponding distinct Frequent Commenters",
      },
    },
  });
};

generate4a();
