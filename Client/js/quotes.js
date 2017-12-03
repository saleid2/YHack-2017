$('#compare').click(function(e){
    e.preventDefault();

    $("#compare_plans_wrapper").empty();
    
    $.get('http://localhost:5000/getplans', function(d){
        data = JSON.parse(d).response.docs;

        output = '';

        data.forEach(function(e){
            output += formatItem(e);
        });
        console.log(output);
        $("#compare_plans_wrapper").append(output);
    
        $("#modal_compare").modal('show');
    });
});

$("#quote").click(function(e){
    e.preventDefault();

    let city = $("#city").val();
    let sex = $("#sex").val();
    let state = $("#state").val();
    let ANNUAL_INCOME = $("#income").val();
    let EMPLOYMENT_STATUS = $("#employment").val();
    let HEIGHT = $("#height").val();
    let WEIGHT = $("#weight").val();
    let TOBACCO = $("#tobacco").val();

    let temp_preconds = $("#preconds").val();
    let PRE_CONDITIONS = temp_preconds.map(function(e){return JSON.parse(e);})

    jsonBuilder = {};
    jsonBuilder.city = city;
    jsonBuilder.sex = sex;
    jsonBuilder.state = state;
    jsonBuilder.ANNUAL_INCOME = ANNUAL_INCOME;
    jsonBuilder.EMPLOYMENT_STATUS = EMPLOYMENT_STATUS;
    jsonBuilder.WEIGHT = WEIGHT;
    jsonBuilder.HEIGHT = HEIGHT;
    jsonBuilder.TOBACCO = TOBACCO;
    // jsonBuilder.PRE_CONDITIONS = PRE_CONDITIONS;

    $.ajax({
        type: "POST",
        url: 'http://localhost:5000/predictuserplan',
        data: JSON.stringify(jsonBuilder),
        dataType: 'json',
        success: function(e){
            console.log(e);
        }
    });
    $("#modal_quote").modal('show');
});

function formatItem(e){
    output = "<div class='panel panel-default'><div class='panel-heading'>" + e.PLAN_NAME + "</div><div class='panel-body'>Coverage for $" + e.COVERAGE_AMOUNT;
    output += "<br>Starting at $" + e.BASE_PRICE + "<br> Deductible: $" + e.DEDUCTIBLE + "<br></div></div>"
    return output;
}