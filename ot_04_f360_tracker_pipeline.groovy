import common.CommonJobs
import common.CommonSettings
import common.CommonTriggers
import common.CommonParameters
import functions.Functions
import common.CommonView
// The parameter 'DisableAll' needs to be injected as environment variable into the SeedJob to work.
try {disabledTemp = DisableAll} catch(e) {disabledTemp = ""}
def disableAutogenerated = !((disabledTemp).equalsIgnoreCase("false"))
def job_configs = [
    ["Jobname"                                , "Repository"                                                       , "Branch"             ,"Jenkinsfile"                                              ],
    ["F360_Tracker_F360Core_MasterPipeline"   , "https://gitgerrit.asux.aptiv.com/CORECOMP/ALSW/OT_ObjectTracking" , "\${params.refspec}" ,"ci/F360/core/master_int_ASTP3.Jenkinsfile"                ],
    ["F360_Tracker_Refactoring_Verification"  , "https://gitgerrit.asux.aptiv.com/CORECOMP/ALSW/OT_ObjectTracking" , "master"             ,"ci/F360/core/general_refactoring_verification.Jenkinsfile"],
    ["F360_Tracker_VDP_HTest_plastic", "https://gitgerrit.asux.aptiv.com/CORECOMP/ALSW/OT_ObjectTracking", "\${params.refspec}" ,"ci/F360/F360_VDP_HIL.Jenkinsfile"                                   ],
]
def configs_map = Functions.convertToListOfMaps(job_configs)
def job_name = "F360_Tracker_Refactoring_Verification"
def pipeline_repository = "https://gitgerrit.asux.aptiv.com/CORECOMP/ALSW/OT_ObjectTracking"
def pipeline_branch = "master"
def jenkinsfile_path = "ci/F360/core/general_refactoring_verification.Jenkinsfile"
configs_map.each { config ->
    def pipeline_job = pipelineJob(config.Jobname)
    CommonJobs.generatePipelineJob(pipeline_job, config.Repository, config.Branch, config.Jenkinsfile, "GSS-GERRIT-gid_ad_oem_integ")
    CommonSettings.disableJob(pipeline_job, disableAutogenerated)
    CommonSettings.setDisplayName(pipeline_job, config.Jobname)
    CommonSettings.setLogRotator(pipeline_job, 60, -1, -1, -1)
    if (config.Jobname == "F360_Tracker_F360Core_MasterPipeline") {
        CommonSettings.disableConcurrentBuild(pipeline_job)
        CommonParameters.addStringParameter(pipeline_job, 'refspec', 'master', 'Git ref to checkout')
    } else if (config.Jobname == "F360_Tracker_Refactoring_Verification"){
        CommonParameters.addStringParameter(pipeline_job, 'GIT_HASH_1', '', 'Git hash before refactoring.')
        CommonParameters.addStringParameter(pipeline_job, 'GIT_HASH_2', '', 'Git hash after refactoring.')
        CommonParameters.addChoiceParameter(pipeline_job, 'CODE_CONFIG', ["F360Core", "OAL"], 'Runs the resim with the target configuration selected.')
        CommonParameters.addChoiceParameter(pipeline_job, 'RESIM_CONFIG', ["Release", "Debug"], 'Builds the resim with the configuration selected.')
    }else if (config.Jobname == "F360_Tracker_VDP_HTest_plastic"){
	CommonParameters.addStringParameter(pipeline_job, 'refspec', 'master', 'refspec to checkout the OT.')
	CommonParameters.addStringParameter(pipeline_job, 'Plastic_Branch', '/main/SOP3_Main_branch/Sprint_branch_for_SOP3_A450_ATS_PLUS_1/j_AZZ-7229', 'Plastic ref of sources to checkout.')
	CommonParameters.addStringParameter(pipeline_job, 'Plastic_CS', 'NO', 'Branch used for the current change in Plastic.')
    }
    // Add single job to the view that has been generated above.
    // CommonView.addSingleJobName(view_01, config.Jobname)
}