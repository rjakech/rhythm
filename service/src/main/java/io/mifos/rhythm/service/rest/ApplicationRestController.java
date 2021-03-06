/*
 * Copyright 2017 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.rhythm.service.rest;

import io.mifos.anubis.annotation.AcceptedTokenType;
import io.mifos.anubis.annotation.Permittable;
import io.mifos.core.command.gateway.CommandGateway;
import io.mifos.core.lang.ServiceException;
import io.mifos.rhythm.api.v1.domain.Application;
import io.mifos.rhythm.service.internal.command.CreateApplicationCommand;
import io.mifos.rhythm.service.internal.command.DeleteApplicationCommand;
import io.mifos.rhythm.service.internal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Myrle Krantz
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/applications")
public class ApplicationRestController {

  private final CommandGateway commandGateway;
  private final ApplicationService applicationService;

  @Autowired
  public ApplicationRestController(final CommandGateway commandGateway,
                                   final ApplicationService applicationService) {
    super();
    this.commandGateway = commandGateway;
    this.applicationService = applicationService;
  }

  @Permittable(value = AcceptedTokenType.SYSTEM)
  @RequestMapping(
          method = RequestMethod.GET,
          consumes = MediaType.ALL_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public
  @ResponseBody
  List<Application> getAllApplications() {
    return this.applicationService.findAllEntities();
  }

  @Permittable(value = AcceptedTokenType.SYSTEM)
  @RequestMapping(
          value = "/{applicationname}",
          method = RequestMethod.GET,
          consumes = MediaType.ALL_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public
  @ResponseBody
  ResponseEntity<Application> getApplication(@PathVariable("applicationname") final String applicationName) {
    return this.applicationService.findByIdentifier(applicationName)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> ServiceException.notFound("Instance with identifier " + applicationName + " doesn't exist."));
  }

  @Permittable(value = AcceptedTokenType.SYSTEM)
  @RequestMapping(
          method = RequestMethod.POST,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public
  @ResponseBody
  ResponseEntity<Void> createApplication(@RequestBody @Valid final Application instance) throws InterruptedException {
    this.commandGateway.process(new CreateApplicationCommand(instance));
    return ResponseEntity.accepted().build();
  }

  @Permittable(value = AcceptedTokenType.SYSTEM)
  @RequestMapping(
          value = "/{applicationname}",
          method = RequestMethod.DELETE,
          consumes = MediaType.ALL_VALUE,
          produces = MediaType.ALL_VALUE
  )
  public
  @ResponseBody
  ResponseEntity<Void> deleteApplication(@PathVariable("applicationname") final String applicationName) throws InterruptedException {
    this.commandGateway.process(new DeleteApplicationCommand(applicationName));
    return ResponseEntity.accepted().build();
  }
}