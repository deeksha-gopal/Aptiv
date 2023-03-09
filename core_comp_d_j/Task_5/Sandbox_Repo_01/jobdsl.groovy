pipelineJob('job_dsl') {

	def repo = 'https://gitgerrit.asux.aptiv.com/a/CORECOMP/ALSW/Sandbox_Repo_01'

  	description("Pipeline for $repo")

  	definition {
	    cpsScm {
      		scm {
        		git {
          			remote { url(repo) }
          			branches('feature/zlib_docker_jenkins')
          			scriptPath('Jenkinsfile')	
				extensions { } 	
			}
		}
	    }
   	}
}
