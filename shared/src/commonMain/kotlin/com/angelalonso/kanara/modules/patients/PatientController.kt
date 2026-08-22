package com.angelalonso.kanara.modules.patients

import com.angelalonso.kanara.db.AppDatabase

class PatientController {
    var database: AppDatabase

    constructor(database: AppDatabase){
        this.database = database
    }

    public fun patientList(): Int {
        return 7
    }
}