notifi = {
    success: function (text) {
        $.notify({
            message: "text"

        }, {
            type: "success", timer: 4000, placement: {from: "top", align: "center"}
        });
    },

    danger: function (text) {
        $.notify({
            message: "text"

        }, {
            type: "danger", timer: 8000, placement: {from: "top", align: "center"}
        });
    },


    successAll: function (messages) {
        if (messages)
            messages.forEach(function (item) {
                notifi.success(item);
            });
    },

    dangerAll: function (messages) {
        if (messages)
            messages.forEach(function (item) {
                notifi.danger(item);
            });
    },
}
