# new feature
# Tags: optional

Feature: Sending messages, images and video
  as a hitbot agent
  I want to send and receive messages, images and videos by agents and contacts respectively
  with the purpose of verifying the adequate sending and receiving of these.


    Background:
        Given the agent is logged in on the Hitbot page

  @SendMessageAgent
  Scenario: Sending messages from an agent's active conversation to a whatsapp contact
    When the agent sends a text message to a contact
    Then the system should verify that the contact receives the text message


  @SendImageAgent
  Scenario: Sending images from an agent's active conversation to a whatsapp contact
    When the agent sends a image to a contact
    Then the system should verify that the contact receives the image

  @SendVideoAgent
  Scenario: Sending video from an agent's active conversation to a whatsapp contact
    When the agent sends a video to a contact
    Then the system should verify that the contact receives the video

  @SendMessageContact
  Scenario: Sending a message from a contact to a whatsapp agent
    When the contact sends a message to a agent
    Then the system should verify that the agent receives the text message

  @SendImageContact
  Scenario: Sending a images from a contact to a whatsapp agent
    When the contact sends a images to a agent
    Then the system should verify that the agent receives the images

  @SendVideoContact
  Scenario: Sending a video from a contact to a whatsapp agent
    When the contact sends a video to a agent
    Then the system should verify that the agent receives the video

