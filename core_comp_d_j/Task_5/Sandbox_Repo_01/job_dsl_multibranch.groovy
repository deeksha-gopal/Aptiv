multibranchPipelineJob('job_dsl_multibranch') {
	def repo = 'https://gitgerrit.asux.aptiv.com/a/CORECOMP/ALSW/Sandbox_Repo_01'
	def cred = 'GSS-GERRIT-gid_ad_oem_integ'
	description("Multibranch Pipeline for $repo")
	
	branchSources{
		branchSource{
			source{
				gerrit{
					id('5caad651-12fb-47da-8cb9-ca0916c65315')
					remote(repo)
					credentialsId(cred)
				}
			}
		}	
   	}

	factory {
          	workflowBranchProjectFactory {
            		scriptPath('Jenkinsfile')
		}
        }

	orphanedItemStrategy {
        	discardOldItems {	
			numToKeep(7)
        	}
    	}
}