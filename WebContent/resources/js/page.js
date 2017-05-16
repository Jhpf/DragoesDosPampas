var Page = JClass._extend({
    init: function (id) {
        this._pageOrder = id;
        this._page = null;

        this.pageFields = null;

        this.initPage();
    },
    initPage: function () {
        switch (this._pageOrder) {
            case 0:
                break;
            case 1:
                this._page = new ListAppsPage(this);
                break;
            case 2:
                break;
            default:
                break;
        }
    },
    initComponents: function() {

    }
});